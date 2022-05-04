package com.android.internal.view;

import android.inputmethodservice.AbstractInputMethodService;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.imetracing.ImeTracing;
import android.util.imetracing.InputConnectionHelper;
import android.util.proto.ProtoOutputStream;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionInspector;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.SurroundingText;
import com.android.internal.inputmethod.CancellationGroup;
import com.android.internal.inputmethod.Completable;
import com.android.internal.inputmethod.ResultCallbacks;
import java.lang.ref.WeakReference;
/* loaded from: classes4.dex */
public class InputConnectionWrapper implements InputConnection {
    private static final int MAX_WAIT_TIME_MILLIS = 2000;
    private static final String TAG = "InputConnectionWrapper";
    private final CancellationGroup mCancellationGroup;
    private final IInputContext mIInputContext;
    private final WeakReference<AbstractInputMethodService> mInputMethodService;
    private final int mMissingMethods;

    public InputConnectionWrapper(WeakReference<AbstractInputMethodService> inputMethodService, IInputContext inputContext, int missingMethods, CancellationGroup cancellationGroup) {
        this.mInputMethodService = inputMethodService;
        this.mIInputContext = inputContext;
        this.mMissingMethods = missingMethods;
        this.mCancellationGroup = cancellationGroup;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextAfterCursor(int length, int flags) {
        if (length < 0 || this.mCancellationGroup.isCanceled()) {
            return null;
        }
        Completable.CharSequence value = Completable.createCharSequence();
        try {
            this.mIInputContext.getTextAfterCursor(length, flags, ResultCallbacks.of(value));
            CharSequence result = (CharSequence) Completable.getResultOrNull(value, TAG, "getTextAfterCursor()", this.mCancellationGroup, 2000);
            AbstractInputMethodService inputMethodService = this.mInputMethodService.get();
            if (inputMethodService != null && ImeTracing.getInstance().isEnabled()) {
                ProtoOutputStream icProto = InputConnectionHelper.buildGetTextAfterCursorProto(length, flags, result);
                ImeTracing.getInstance().triggerServiceDump("InputConnectionWrapper#getTextAfterCursor", inputMethodService, icProto);
            }
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int length, int flags) {
        if (length < 0 || this.mCancellationGroup.isCanceled()) {
            return null;
        }
        Completable.CharSequence value = Completable.createCharSequence();
        try {
            this.mIInputContext.getTextBeforeCursor(length, flags, ResultCallbacks.of(value));
            CharSequence result = (CharSequence) Completable.getResultOrNull(value, TAG, "getTextBeforeCursor()", this.mCancellationGroup, 2000);
            AbstractInputMethodService inputMethodService = this.mInputMethodService.get();
            if (inputMethodService != null && ImeTracing.getInstance().isEnabled()) {
                ProtoOutputStream icProto = InputConnectionHelper.buildGetTextBeforeCursorProto(length, flags, result);
                ImeTracing.getInstance().triggerServiceDump("InputConnectionWrapper#getTextBeforeCursor", inputMethodService, icProto);
            }
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getSelectedText(int flags) {
        if (this.mCancellationGroup.isCanceled() || isMethodMissing(1)) {
            return null;
        }
        Completable.CharSequence value = Completable.createCharSequence();
        try {
            this.mIInputContext.getSelectedText(flags, ResultCallbacks.of(value));
            CharSequence result = (CharSequence) Completable.getResultOrNull(value, TAG, "getSelectedText()", this.mCancellationGroup, 2000);
            AbstractInputMethodService inputMethodService = this.mInputMethodService.get();
            if (inputMethodService != null && ImeTracing.getInstance().isEnabled()) {
                ProtoOutputStream icProto = InputConnectionHelper.buildGetSelectedTextProto(flags, result);
                ImeTracing.getInstance().triggerServiceDump("InputConnectionWrapper#getSelectedText", inputMethodService, icProto);
            }
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public SurroundingText getSurroundingText(int beforeLength, int afterLength, int flags) {
        if (beforeLength < 0 || afterLength < 0 || this.mCancellationGroup.isCanceled() || isMethodMissing(256)) {
            return null;
        }
        Completable.SurroundingText value = Completable.createSurroundingText();
        try {
            this.mIInputContext.getSurroundingText(beforeLength, afterLength, flags, ResultCallbacks.of(value));
            SurroundingText result = (SurroundingText) Completable.getResultOrNull(value, TAG, "getSurroundingText()", this.mCancellationGroup, 2000);
            AbstractInputMethodService inputMethodService = this.mInputMethodService.get();
            if (inputMethodService != null && ImeTracing.getInstance().isEnabled()) {
                ProtoOutputStream icProto = InputConnectionHelper.buildGetSurroundingTextProto(beforeLength, afterLength, flags, result);
                ImeTracing.getInstance().triggerServiceDump("InputConnectionWrapper#getSurroundingText", inputMethodService, icProto);
            }
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public int getCursorCapsMode(int reqModes) {
        if (this.mCancellationGroup.isCanceled()) {
            return 0;
        }
        Completable.Int value = Completable.createInt();
        try {
            this.mIInputContext.getCursorCapsMode(reqModes, ResultCallbacks.of(value));
            int result = Completable.getResultOrZero(value, TAG, "getCursorCapsMode()", this.mCancellationGroup, 2000);
            AbstractInputMethodService inputMethodService = this.mInputMethodService.get();
            if (inputMethodService != null && ImeTracing.getInstance().isEnabled()) {
                ProtoOutputStream icProto = InputConnectionHelper.buildGetCursorCapsModeProto(reqModes, result);
                ImeTracing.getInstance().triggerServiceDump("InputConnectionWrapper#getCursorCapsMode", inputMethodService, icProto);
            }
            return result;
        } catch (RemoteException e) {
            return 0;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        if (this.mCancellationGroup.isCanceled()) {
            return null;
        }
        Completable.ExtractedText value = Completable.createExtractedText();
        try {
            this.mIInputContext.getExtractedText(request, flags, ResultCallbacks.of(value));
            ExtractedText result = (ExtractedText) Completable.getResultOrNull(value, TAG, "getExtractedText()", this.mCancellationGroup, 2000);
            AbstractInputMethodService inputMethodService = this.mInputMethodService.get();
            if (inputMethodService != null && ImeTracing.getInstance().isEnabled()) {
                ProtoOutputStream icProto = InputConnectionHelper.buildGetExtractedTextProto(request, flags, result);
                ImeTracing.getInstance().triggerServiceDump("InputConnectionWrapper#getExtractedText", inputMethodService, icProto);
            }
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence text, int newCursorPosition) {
        try {
            this.mIInputContext.commitText(text, newCursorPosition);
            notifyUserActionIfNecessary();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    private void notifyUserActionIfNecessary() {
        AbstractInputMethodService inputMethodService = this.mInputMethodService.get();
        if (inputMethodService != null) {
            inputMethodService.notifyUserActionIfNecessary();
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCompletion(CompletionInfo text) {
        if (isMethodMissing(4)) {
            return false;
        }
        try {
            this.mIInputContext.commitCompletion(text);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        try {
            this.mIInputContext.commitCorrection(correctionInfo);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setSelection(int start, int end) {
        try {
            this.mIInputContext.setSelection(start, end);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performEditorAction(int actionCode) {
        try {
            this.mIInputContext.performEditorAction(actionCode);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int id) {
        try {
            this.mIInputContext.performContextMenuAction(id);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int start, int end) {
        if (isMethodMissing(2)) {
            return false;
        }
        try {
            this.mIInputContext.setComposingRegion(start, end);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        try {
            this.mIInputContext.setComposingText(text, newCursorPosition);
            notifyUserActionIfNecessary();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        try {
            this.mIInputContext.finishComposingText();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        try {
            this.mIInputContext.beginBatchEdit();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        try {
            this.mIInputContext.endBatchEdit();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent event) {
        try {
            this.mIInputContext.sendKeyEvent(event);
            notifyUserActionIfNecessary();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int states) {
        try {
            this.mIInputContext.clearMetaKeyStates(states);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        try {
            this.mIInputContext.deleteSurroundingText(beforeLength, afterLength);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
        if (isMethodMissing(16)) {
            return false;
        }
        try {
            this.mIInputContext.deleteSurroundingTextInCodePoints(beforeLength, afterLength);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean reportFullscreenMode(boolean enabled) {
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performSpellCheck() {
        try {
            this.mIInputContext.performSpellCheck();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String action, Bundle data) {
        try {
            this.mIInputContext.performPrivateCommand(action, data);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int cursorUpdateMode) {
        if (this.mCancellationGroup.isCanceled() || isMethodMissing(8)) {
            return false;
        }
        Completable.Int value = Completable.createInt();
        try {
            this.mIInputContext.requestUpdateCursorAnchorInfo(cursorUpdateMode, ResultCallbacks.of(value));
            return Completable.getResultOrZero(value, TAG, "requestUpdateCursorAnchorInfo()", this.mCancellationGroup, 2000) != 0;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public void closeConnection() {
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) {
        if (this.mCancellationGroup.isCanceled() || isMethodMissing(128)) {
            return false;
        }
        if ((flags & 1) != 0) {
            AbstractInputMethodService inputMethodService = this.mInputMethodService.get();
            if (inputMethodService == null) {
                return false;
            }
            inputMethodService.exposeContent(inputContentInfo, this);
        }
        Completable.Int value = Completable.createInt();
        try {
            this.mIInputContext.commitContent(inputContentInfo, flags, opts, ResultCallbacks.of(value));
            return Completable.getResultOrZero(value, TAG, "commitContent()", this.mCancellationGroup, 2000) != 0;
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setImeConsumesInput(boolean imeConsumesInput) {
        try {
            this.mIInputContext.setImeConsumesInput(imeConsumesInput);
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

    private boolean isMethodMissing(int methodFlag) {
        return (this.mMissingMethods & methodFlag) == methodFlag;
    }

    public String toString() {
        return "InputConnectionWrapper{idHash=#" + Integer.toHexString(System.identityHashCode(this)) + " mMissingMethods=" + InputConnectionInspector.getMissingMethodFlagsAsString(this.mMissingMethods) + "}";
    }
}
