package android.inputmethodservice;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.InputMethodSession;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import com.android.internal.view.IInputMethodSession;
/* loaded from: classes2.dex */
class IInputMethodSessionWrapper extends IInputMethodSession.Stub implements HandlerCaller.Callback {
    private static final int DO_APP_PRIVATE_COMMAND = 100;
    private static final int DO_DISPLAY_COMPLETIONS = 65;
    private static final int DO_FINISH_INPUT = 140;
    private static final int DO_FINISH_SESSION = 110;
    private static final int DO_NOTIFY_IME_HIDDEN = 120;
    private static final int DO_REMOVE_IME_SURFACE = 130;
    private static final int DO_UPDATE_CURSOR = 95;
    private static final int DO_UPDATE_CURSOR_ANCHOR_INFO = 99;
    private static final int DO_UPDATE_EXTRACTED_TEXT = 67;
    private static final int DO_UPDATE_SELECTION = 90;
    private static final int DO_VIEW_CLICKED = 115;
    private static final String TAG = "InputMethodWrapper";
    HandlerCaller mCaller;
    InputChannel mChannel;
    InputMethodSession mInputMethodSession;
    ImeInputEventReceiver mReceiver;

    public IInputMethodSessionWrapper(Context context, InputMethodSession inputMethodSession, InputChannel channel) {
        this.mCaller = new HandlerCaller(context, null, this, true);
        this.mInputMethodSession = inputMethodSession;
        this.mChannel = channel;
        if (channel != null) {
            this.mReceiver = new ImeInputEventReceiver(channel, context.getMainLooper());
        }
    }

    public InputMethodSession getInternalInputMethodSession() {
        return this.mInputMethodSession;
    }

    @Override // com.android.internal.os.HandlerCaller.Callback
    public void executeMessage(Message msg) {
        if (this.mInputMethodSession == null) {
            switch (msg.what) {
                case 90:
                case 100:
                    ((SomeArgs) msg.obj).recycle();
                    return;
                default:
                    return;
            }
        } else {
            switch (msg.what) {
                case 65:
                    this.mInputMethodSession.displayCompletions((CompletionInfo[]) msg.obj);
                    return;
                case 67:
                    this.mInputMethodSession.updateExtractedText(msg.arg1, (ExtractedText) msg.obj);
                    return;
                case 90:
                    SomeArgs args = (SomeArgs) msg.obj;
                    this.mInputMethodSession.updateSelection(args.argi1, args.argi2, args.argi3, args.argi4, args.argi5, args.argi6);
                    args.recycle();
                    return;
                case 95:
                    this.mInputMethodSession.updateCursor((Rect) msg.obj);
                    return;
                case 99:
                    this.mInputMethodSession.updateCursorAnchorInfo((CursorAnchorInfo) msg.obj);
                    return;
                case 100:
                    SomeArgs args2 = (SomeArgs) msg.obj;
                    this.mInputMethodSession.appPrivateCommand((String) args2.arg1, (Bundle) args2.arg2);
                    args2.recycle();
                    return;
                case 110:
                    doFinishSession();
                    return;
                case 115:
                    InputMethodSession inputMethodSession = this.mInputMethodSession;
                    boolean z = true;
                    if (msg.arg1 != 1) {
                        z = false;
                    }
                    inputMethodSession.viewClicked(z);
                    return;
                case 120:
                    this.mInputMethodSession.notifyImeHidden();
                    return;
                case 130:
                    this.mInputMethodSession.removeImeSurface();
                    return;
                case 140:
                    this.mInputMethodSession.finishInput();
                    return;
                default:
                    Log.w(TAG, "Unhandled message code: " + msg.what);
                    return;
            }
        }
    }

    private void doFinishSession() {
        this.mInputMethodSession = null;
        ImeInputEventReceiver imeInputEventReceiver = this.mReceiver;
        if (imeInputEventReceiver != null) {
            imeInputEventReceiver.dispose();
            this.mReceiver = null;
        }
        InputChannel inputChannel = this.mChannel;
        if (inputChannel != null) {
            inputChannel.dispose();
            this.mChannel = null;
        }
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void displayCompletions(CompletionInfo[] completions) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageO(65, completions));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void updateExtractedText(int token, ExtractedText text) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageIO(67, token, text));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void updateSelection(int oldSelStart, int oldSelEnd, int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageIIIIII(90, oldSelStart, oldSelEnd, newSelStart, newSelEnd, candidatesStart, candidatesEnd));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void viewClicked(boolean focusChanged) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageI(115, focusChanged ? 1 : 0));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void notifyImeHidden() {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessage(120));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void removeImeSurface() {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessage(130));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void updateCursor(Rect newCursor) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageO(95, newCursor));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void updateCursorAnchorInfo(CursorAnchorInfo cursorAnchorInfo) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageO(99, cursorAnchorInfo));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void appPrivateCommand(String action, Bundle data) {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessageOO(100, action, data));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void finishSession() {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessage(110));
    }

    @Override // com.android.internal.view.IInputMethodSession
    public void finishInput() {
        HandlerCaller handlerCaller = this.mCaller;
        handlerCaller.executeOrSendMessage(handlerCaller.obtainMessage(140));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class ImeInputEventReceiver extends InputEventReceiver implements InputMethodSession.EventCallback {
        private final SparseArray<InputEvent> mPendingEvents = new SparseArray<>();

        public ImeInputEventReceiver(InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper);
        }

        @Override // android.view.InputEventReceiver
        public void onInputEvent(InputEvent event) {
            if (IInputMethodSessionWrapper.this.mInputMethodSession == null) {
                finishInputEvent(event, false);
                return;
            }
            int seq = event.getSequenceNumber();
            this.mPendingEvents.put(seq, event);
            if (event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                IInputMethodSessionWrapper.this.mInputMethodSession.dispatchKeyEvent(seq, keyEvent, this);
                return;
            }
            MotionEvent motionEvent = (MotionEvent) event;
            if (motionEvent.isFromSource(4)) {
                IInputMethodSessionWrapper.this.mInputMethodSession.dispatchTrackballEvent(seq, motionEvent, this);
            } else {
                IInputMethodSessionWrapper.this.mInputMethodSession.dispatchGenericMotionEvent(seq, motionEvent, this);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession.EventCallback
        public void finishedEvent(int seq, boolean handled) {
            int index = this.mPendingEvents.indexOfKey(seq);
            if (index >= 0) {
                InputEvent event = this.mPendingEvents.valueAt(index);
                this.mPendingEvents.removeAt(index);
                finishInputEvent(event, handled);
            }
        }
    }
}
