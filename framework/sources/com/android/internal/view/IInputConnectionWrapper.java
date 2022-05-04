package com.android.internal.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.proto.ProtoOutputStream;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.DumpableInputConnection;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.inputmethod.ICharSequenceResultCallback;
import com.android.internal.inputmethod.IExtractedTextResultCallback;
import com.android.internal.inputmethod.IIntResultCallback;
import com.android.internal.inputmethod.ISurroundingTextResultCallback;
import com.android.internal.os.SomeArgs;
import com.android.internal.view.IInputContext;
import java.lang.ref.WeakReference;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class IInputConnectionWrapper extends IInputContext.Stub {
    private static final boolean DEBUG = false;
    private static final int DO_BEGIN_BATCH_EDIT = 90;
    private static final int DO_CLEAR_META_KEY_STATES = 130;
    private static final int DO_CLOSE_CONNECTION = 150;
    private static final int DO_COMMIT_COMPLETION = 55;
    private static final int DO_COMMIT_CONTENT = 160;
    private static final int DO_COMMIT_CORRECTION = 56;
    private static final int DO_COMMIT_TEXT = 50;
    private static final int DO_DELETE_SURROUNDING_TEXT = 80;
    private static final int DO_DELETE_SURROUNDING_TEXT_IN_CODE_POINTS = 81;
    private static final int DO_END_BATCH_EDIT = 95;
    private static final int DO_FINISH_COMPOSING_TEXT = 65;
    private static final int DO_GET_CURSOR_CAPS_MODE = 30;
    private static final int DO_GET_EXTRACTED_TEXT = 40;
    private static final int DO_GET_SELECTED_TEXT = 25;
    private static final int DO_GET_SURROUNDING_TEXT = 41;
    private static final int DO_GET_TEXT_AFTER_CURSOR = 10;
    private static final int DO_GET_TEXT_BEFORE_CURSOR = 20;
    private static final int DO_PERFORM_CONTEXT_MENU_ACTION = 59;
    private static final int DO_PERFORM_EDITOR_ACTION = 58;
    private static final int DO_PERFORM_PRIVATE_COMMAND = 120;
    private static final int DO_PERFORM_SPELL_CHECK = 110;
    private static final int DO_REQUEST_UPDATE_CURSOR_ANCHOR_INFO = 140;
    private static final int DO_SEND_KEY_EVENT = 70;
    private static final int DO_SET_COMPOSING_REGION = 63;
    private static final int DO_SET_COMPOSING_TEXT = 60;
    private static final int DO_SET_IME_CONSUMES_INPUT = 170;
    private static final int DO_SET_SELECTION = 57;
    private static final String TAG = "IInputConnectionWrapper";
    private Handler mH;
    private InputConnection mInputConnection;
    private Looper mMainLooper;
    private final InputMethodManager mParentInputMethodManager;
    private final WeakReference<View> mServedView;
    private final Object mLock = new Object();
    private boolean mFinished = false;

    /* loaded from: classes4.dex */
    class MyHandler extends Handler {
        MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            IInputConnectionWrapper.this.executeMessage(msg);
        }
    }

    public IInputConnectionWrapper(Looper mainLooper, InputConnection inputConnection, InputMethodManager inputMethodManager, View servedView) {
        this.mInputConnection = inputConnection;
        this.mMainLooper = mainLooper;
        this.mH = new MyHandler(this.mMainLooper);
        this.mParentInputMethodManager = inputMethodManager;
        this.mServedView = new WeakReference<>(servedView);
    }

    public InputConnection getInputConnection() {
        InputConnection inputConnection;
        synchronized (this.mLock) {
            inputConnection = this.mInputConnection;
        }
        return inputConnection;
    }

    private boolean isFinished() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mFinished;
        }
        return z;
    }

    public boolean isActive() {
        return this.mParentInputMethodManager.isActive() && !isFinished();
    }

    public View getServedView() {
        return this.mServedView.get();
    }

    public void deactivate() {
        Handler handler;
        if (!isFinished()) {
            closeConnection();
            final View servedView = this.mServedView.get();
            if (servedView != null && (handler = servedView.getHandler()) != null) {
                if (handler.getLooper().isCurrentThread()) {
                    servedView.onInputConnectionClosedInternal();
                    return;
                }
                Objects.requireNonNull(servedView);
                handler.post(new Runnable() { // from class: com.android.internal.view.IInputConnectionWrapper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        View.this.onInputConnectionClosedInternal();
                    }
                });
            }
        }
    }

    public String toString() {
        return "IInputConnectionWrapper{connection=" + getInputConnection() + " finished=" + isFinished() + " mParentInputMethodManager.isActive()=" + this.mParentInputMethodManager.isActive() + " mServedView=" + this.mServedView.get() + "}";
    }

    public void dumpDebug(ProtoOutputStream proto, long fieldId) {
        synchronized (this.mLock) {
            if ((this.mInputConnection instanceof DumpableInputConnection) && Looper.myLooper() == this.mMainLooper) {
                ((DumpableInputConnection) this.mInputConnection).dumpDebug(proto, fieldId);
            }
        }
    }

    @Override // com.android.internal.view.IInputContext
    public void getTextAfterCursor(int length, int flags, ICharSequenceResultCallback callback) {
        dispatchMessage(this.mH.obtainMessage(10, length, flags, callback));
    }

    @Override // com.android.internal.view.IInputContext
    public void getTextBeforeCursor(int length, int flags, ICharSequenceResultCallback callback) {
        dispatchMessage(this.mH.obtainMessage(20, length, flags, callback));
    }

    @Override // com.android.internal.view.IInputContext
    public void getSelectedText(int flags, ICharSequenceResultCallback callback) {
        dispatchMessage(this.mH.obtainMessage(25, flags, 0, callback));
    }

    @Override // com.android.internal.view.IInputContext
    public void getSurroundingText(int beforeLength, int afterLength, int flags, ISurroundingTextResultCallback callback) {
        SomeArgs args = SomeArgs.obtain();
        args.arg1 = Integer.valueOf(beforeLength);
        args.arg2 = Integer.valueOf(afterLength);
        args.arg3 = Integer.valueOf(flags);
        args.arg4 = callback;
        dispatchMessage(this.mH.obtainMessage(41, flags, 0, args));
    }

    @Override // com.android.internal.view.IInputContext
    public void getCursorCapsMode(int reqModes, IIntResultCallback callback) {
        dispatchMessage(this.mH.obtainMessage(30, reqModes, 0, callback));
    }

    @Override // com.android.internal.view.IInputContext
    public void getExtractedText(ExtractedTextRequest request, int flags, IExtractedTextResultCallback callback) {
        SomeArgs args = SomeArgs.obtain();
        args.arg1 = request;
        args.arg2 = callback;
        dispatchMessage(this.mH.obtainMessage(40, flags, 0, args));
    }

    @Override // com.android.internal.view.IInputContext
    public void commitText(CharSequence text, int newCursorPosition) {
        dispatchMessage(obtainMessageIO(50, newCursorPosition, text));
    }

    @Override // com.android.internal.view.IInputContext
    public void commitCompletion(CompletionInfo text) {
        dispatchMessage(obtainMessageO(55, text));
    }

    @Override // com.android.internal.view.IInputContext
    public void commitCorrection(CorrectionInfo info) {
        dispatchMessage(obtainMessageO(56, info));
    }

    @Override // com.android.internal.view.IInputContext
    public void setSelection(int start, int end) {
        dispatchMessage(obtainMessageII(57, start, end));
    }

    @Override // com.android.internal.view.IInputContext
    public void performEditorAction(int id) {
        dispatchMessage(obtainMessageII(58, id, 0));
    }

    @Override // com.android.internal.view.IInputContext
    public void performContextMenuAction(int id) {
        dispatchMessage(obtainMessageII(59, id, 0));
    }

    @Override // com.android.internal.view.IInputContext
    public void setComposingRegion(int start, int end) {
        dispatchMessage(obtainMessageII(63, start, end));
    }

    @Override // com.android.internal.view.IInputContext
    public void setComposingText(CharSequence text, int newCursorPosition) {
        dispatchMessage(obtainMessageIO(60, newCursorPosition, text));
    }

    @Override // com.android.internal.view.IInputContext
    public void finishComposingText() {
        dispatchMessage(obtainMessage(65));
    }

    @Override // com.android.internal.view.IInputContext
    public void sendKeyEvent(KeyEvent event) {
        dispatchMessage(obtainMessageO(70, event));
    }

    @Override // com.android.internal.view.IInputContext
    public void clearMetaKeyStates(int states) {
        dispatchMessage(obtainMessageII(130, states, 0));
    }

    @Override // com.android.internal.view.IInputContext
    public void deleteSurroundingText(int beforeLength, int afterLength) {
        dispatchMessage(obtainMessageII(80, beforeLength, afterLength));
    }

    @Override // com.android.internal.view.IInputContext
    public void deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
        dispatchMessage(obtainMessageII(81, beforeLength, afterLength));
    }

    @Override // com.android.internal.view.IInputContext
    public void beginBatchEdit() {
        dispatchMessage(obtainMessage(90));
    }

    @Override // com.android.internal.view.IInputContext
    public void endBatchEdit() {
        dispatchMessage(obtainMessage(95));
    }

    @Override // com.android.internal.view.IInputContext
    public void performSpellCheck() {
        dispatchMessage(obtainMessage(110));
    }

    @Override // com.android.internal.view.IInputContext
    public void performPrivateCommand(String action, Bundle data) {
        dispatchMessage(obtainMessageOO(120, action, data));
    }

    @Override // com.android.internal.view.IInputContext
    public void requestUpdateCursorAnchorInfo(int cursorUpdateMode, IIntResultCallback callback) {
        dispatchMessage(this.mH.obtainMessage(140, cursorUpdateMode, 0, callback));
    }

    public void closeConnection() {
        dispatchMessage(obtainMessage(150));
    }

    @Override // com.android.internal.view.IInputContext
    public void commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts, IIntResultCallback callback) {
        SomeArgs args = SomeArgs.obtain();
        args.arg1 = inputContentInfo;
        args.arg2 = opts;
        args.arg3 = callback;
        dispatchMessage(this.mH.obtainMessage(160, flags, 0, args));
    }

    @Override // com.android.internal.view.IInputContext
    public void setImeConsumesInput(boolean imeConsumesInput) {
        dispatchMessage(obtainMessageB(170, imeConsumesInput));
    }

    void dispatchMessage(Message msg) {
        if (Looper.myLooper() == this.mMainLooper) {
            executeMessage(msg);
            msg.recycle();
            return;
        }
        this.mH.sendMessage(msg);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:498|438|(7:443|445|(1:447)|479|448|451|452)|444|445|(0)|479|448|451|452) */
    /* JADX WARN: Can't wrap try/catch for region: R(10:504|381|(7:386|388|(1:390)|487|391|394|395)|387|388|(0)|487|391|394|395) */
    /* JADX WARN: Can't wrap try/catch for region: R(10:510|25|(8:30|(6:35|(1:40)|494|41|44|45)|36|(0)|494|41|44|45)|37|38|(0)|494|41|44|45) */
    /* JADX WARN: Can't wrap try/catch for region: R(10:519|419|(7:424|426|(1:428)|500|429|432|433)|425|426|(0)|500|429|432|433) */
    /* JADX WARN: Can't wrap try/catch for region: R(10:521|362|(7:367|369|(1:371)|489|372|375|376)|368|369|(0)|489|372|375|376) */
    /* JADX WARN: Can't wrap try/catch for region: R(11:399|484|400|(7:405|407|(1:409)|515|410|413|414)|406|407|(0)|515|410|413|414) */
    /* JADX WARN: Can't wrap try/catch for region: R(11:456|476|457|(7:462|464|(1:466)|508|467|470|471)|463|464|(0)|508|467|470|471) */
    /* JADX WARN: Can't wrap try/catch for region: R(11:86|482|87|(6:92|(1:96)|526|97|100|101)|93|94|(0)|526|97|100|101) */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x04f3, code lost:
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x04f4, code lost:
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to getSurroundingText(). result=" + r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:392:0x0566, code lost:
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x0567, code lost:
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to getExtractedText(). result=" + r6, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x05d1, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:412:0x05d2, code lost:
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to getCursorCapsMode(). result=" + r2, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ac, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x0636, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:431:0x0637, code lost:
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to getSelectedText(). result=" + ((java.lang.Object) r2), r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ad, code lost:
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to commitContent(). result=" + r9, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:0x069f, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:450:0x06a0, code lost:
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to getTextBeforeCursor(). result=" + ((java.lang.Object) r2), r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:468:0x0708, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:469:0x0709, code lost:
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to getTextAfterCursor(). result=" + ((java.lang.Object) r2), r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x014d, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x014e, code lost:
        android.util.Log.w(com.android.internal.view.IInputConnectionWrapper.TAG, "Failed to return the result to requestCursorUpdates(). result=" + r6, r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:371:0x04e0 A[Catch: all -> 0x0512, TRY_LEAVE, TryCatch #29 {all -> 0x0512, blocks: (B:362:0x04a0, B:364:0x04c2, B:367:0x04c9, B:368:0x04ce, B:369:0x04d6, B:371:0x04e0, B:372:0x04ef, B:374:0x04f4), top: B:521:0x04a0, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0551 A[Catch: all -> 0x0585, TRY_LEAVE, TryCatch #19 {all -> 0x0585, blocks: (B:381:0x0523, B:383:0x0531, B:386:0x0538, B:387:0x053f, B:388:0x0547, B:390:0x0551, B:391:0x0562, B:393:0x0567), top: B:504:0x0523, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:409:0x05bc A[Catch: all -> 0x05ed, TRY_LEAVE, TryCatch #6 {all -> 0x05ed, blocks: (B:400:0x0592, B:402:0x059c, B:405:0x05a3, B:406:0x05aa, B:407:0x05b2, B:409:0x05bc, B:410:0x05cd, B:412:0x05d2), top: B:484:0x0592, inners: #26 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0621 A[Catch: all -> 0x0652, TRY_LEAVE, TryCatch #28 {all -> 0x0652, blocks: (B:419:0x05f7, B:421:0x0601, B:424:0x0608, B:425:0x060f, B:426:0x0617, B:428:0x0621, B:429:0x0632, B:431:0x0637), top: B:519:0x05f7, inners: #16 }] */
    /* JADX WARN: Removed duplicated region for block: B:447:0x0688 A[Catch: all -> 0x06bb, TRY_LEAVE, TryCatch #15 {all -> 0x06bb, blocks: (B:438:0x065c, B:440:0x0666, B:443:0x066d, B:444:0x0676, B:445:0x067e, B:447:0x0688, B:448:0x069b, B:450:0x06a0), top: B:498:0x065c, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:466:0x06f1 A[Catch: all -> 0x0724, TRY_LEAVE, TryCatch #1 {all -> 0x0724, blocks: (B:457:0x06c5, B:459:0x06cf, B:462:0x06d6, B:463:0x06df, B:464:0x06e7, B:466:0x06f1, B:467:0x0704, B:469:0x0709), top: B:476:0x06c5, inners: #22 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void executeMessage(android.os.Message r14) {
        /*
            Method dump skipped, instructions count: 1944
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.view.IInputConnectionWrapper.executeMessage(android.os.Message):void");
    }

    Message obtainMessage(int what) {
        return this.mH.obtainMessage(what);
    }

    Message obtainMessageII(int what, int arg1, int arg2) {
        return this.mH.obtainMessage(what, arg1, arg2);
    }

    Message obtainMessageO(int what, Object arg1) {
        return this.mH.obtainMessage(what, 0, 0, arg1);
    }

    Message obtainMessageIO(int what, int arg1, Object arg2) {
        return this.mH.obtainMessage(what, arg1, 0, arg2);
    }

    Message obtainMessageOO(int what, Object arg1, Object arg2) {
        SomeArgs args = SomeArgs.obtain();
        args.arg1 = arg1;
        args.arg2 = arg2;
        return this.mH.obtainMessage(what, 0, 0, args);
    }

    Message obtainMessageB(int what, boolean arg1) {
        return this.mH.obtainMessage(what, arg1 ? 1 : 0, 0);
    }
}
