package com.android.internal.view;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputContentInfo;
import com.android.internal.inputmethod.ICharSequenceResultCallback;
import com.android.internal.inputmethod.IExtractedTextResultCallback;
import com.android.internal.inputmethod.IIntResultCallback;
import com.android.internal.inputmethod.ISurroundingTextResultCallback;
/* loaded from: classes4.dex */
public interface IInputContext extends IInterface {
    void beginBatchEdit() throws RemoteException;

    void clearMetaKeyStates(int i) throws RemoteException;

    void commitCompletion(CompletionInfo completionInfo) throws RemoteException;

    void commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle, IIntResultCallback iIntResultCallback) throws RemoteException;

    void commitCorrection(CorrectionInfo correctionInfo) throws RemoteException;

    void commitText(CharSequence charSequence, int i) throws RemoteException;

    void deleteSurroundingText(int i, int i2) throws RemoteException;

    void deleteSurroundingTextInCodePoints(int i, int i2) throws RemoteException;

    void endBatchEdit() throws RemoteException;

    void finishComposingText() throws RemoteException;

    void getCursorCapsMode(int i, IIntResultCallback iIntResultCallback) throws RemoteException;

    void getExtractedText(ExtractedTextRequest extractedTextRequest, int i, IExtractedTextResultCallback iExtractedTextResultCallback) throws RemoteException;

    void getSelectedText(int i, ICharSequenceResultCallback iCharSequenceResultCallback) throws RemoteException;

    void getSurroundingText(int i, int i2, int i3, ISurroundingTextResultCallback iSurroundingTextResultCallback) throws RemoteException;

    void getTextAfterCursor(int i, int i2, ICharSequenceResultCallback iCharSequenceResultCallback) throws RemoteException;

    void getTextBeforeCursor(int i, int i2, ICharSequenceResultCallback iCharSequenceResultCallback) throws RemoteException;

    void performContextMenuAction(int i) throws RemoteException;

    void performEditorAction(int i) throws RemoteException;

    void performPrivateCommand(String str, Bundle bundle) throws RemoteException;

    void performSpellCheck() throws RemoteException;

    void requestUpdateCursorAnchorInfo(int i, IIntResultCallback iIntResultCallback) throws RemoteException;

    void sendKeyEvent(KeyEvent keyEvent) throws RemoteException;

    void setComposingRegion(int i, int i2) throws RemoteException;

    void setComposingText(CharSequence charSequence, int i) throws RemoteException;

    void setImeConsumesInput(boolean z) throws RemoteException;

    void setSelection(int i, int i2) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IInputContext {
        @Override // com.android.internal.view.IInputContext
        public void getTextBeforeCursor(int length, int flags, ICharSequenceResultCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void getTextAfterCursor(int length, int flags, ICharSequenceResultCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void getCursorCapsMode(int reqModes, IIntResultCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void getExtractedText(ExtractedTextRequest request, int flags, IExtractedTextResultCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void deleteSurroundingText(int beforeLength, int afterLength) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void setComposingText(CharSequence text, int newCursorPosition) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void finishComposingText() throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void commitText(CharSequence text, int newCursorPosition) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void commitCompletion(CompletionInfo completion) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void commitCorrection(CorrectionInfo correction) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void setSelection(int start, int end) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void performEditorAction(int actionCode) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void performContextMenuAction(int id) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void beginBatchEdit() throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void endBatchEdit() throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void sendKeyEvent(KeyEvent event) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void clearMetaKeyStates(int states) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void performSpellCheck() throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void performPrivateCommand(String action, Bundle data) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void setComposingRegion(int start, int end) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void getSelectedText(int flags, ICharSequenceResultCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void requestUpdateCursorAnchorInfo(int cursorUpdateMode, IIntResultCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts, IIntResultCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void getSurroundingText(int beforeLength, int afterLength, int flags, ISurroundingTextResultCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputContext
        public void setImeConsumesInput(boolean imeConsumesInput) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IInputContext {
        public static final String DESCRIPTOR = "com.android.internal.view.IInputContext";
        static final int TRANSACTION_beginBatchEdit = 15;
        static final int TRANSACTION_clearMetaKeyStates = 18;
        static final int TRANSACTION_commitCompletion = 10;
        static final int TRANSACTION_commitContent = 24;
        static final int TRANSACTION_commitCorrection = 11;
        static final int TRANSACTION_commitText = 9;
        static final int TRANSACTION_deleteSurroundingText = 5;
        static final int TRANSACTION_deleteSurroundingTextInCodePoints = 6;
        static final int TRANSACTION_endBatchEdit = 16;
        static final int TRANSACTION_finishComposingText = 8;
        static final int TRANSACTION_getCursorCapsMode = 3;
        static final int TRANSACTION_getExtractedText = 4;
        static final int TRANSACTION_getSelectedText = 22;
        static final int TRANSACTION_getSurroundingText = 25;
        static final int TRANSACTION_getTextAfterCursor = 2;
        static final int TRANSACTION_getTextBeforeCursor = 1;
        static final int TRANSACTION_performContextMenuAction = 14;
        static final int TRANSACTION_performEditorAction = 13;
        static final int TRANSACTION_performPrivateCommand = 20;
        static final int TRANSACTION_performSpellCheck = 19;
        static final int TRANSACTION_requestUpdateCursorAnchorInfo = 23;
        static final int TRANSACTION_sendKeyEvent = 17;
        static final int TRANSACTION_setComposingRegion = 21;
        static final int TRANSACTION_setComposingText = 7;
        static final int TRANSACTION_setImeConsumesInput = 26;
        static final int TRANSACTION_setSelection = 12;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInputContext asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IInputContext)) {
                return new Proxy(obj);
            }
            return (IInputContext) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getTextBeforeCursor";
                case 2:
                    return "getTextAfterCursor";
                case 3:
                    return "getCursorCapsMode";
                case 4:
                    return "getExtractedText";
                case 5:
                    return "deleteSurroundingText";
                case 6:
                    return "deleteSurroundingTextInCodePoints";
                case 7:
                    return "setComposingText";
                case 8:
                    return "finishComposingText";
                case 9:
                    return "commitText";
                case 10:
                    return "commitCompletion";
                case 11:
                    return "commitCorrection";
                case 12:
                    return "setSelection";
                case 13:
                    return "performEditorAction";
                case 14:
                    return "performContextMenuAction";
                case 15:
                    return "beginBatchEdit";
                case 16:
                    return "endBatchEdit";
                case 17:
                    return "sendKeyEvent";
                case 18:
                    return "clearMetaKeyStates";
                case 19:
                    return "performSpellCheck";
                case 20:
                    return "performPrivateCommand";
                case 21:
                    return "setComposingRegion";
                case 22:
                    return "getSelectedText";
                case 23:
                    return "requestUpdateCursorAnchorInfo";
                case 24:
                    return "commitContent";
                case 25:
                    return "getSurroundingText";
                case 26:
                    return "setImeConsumesInput";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ExtractedTextRequest _arg0;
            CharSequence _arg02;
            CharSequence _arg03;
            CompletionInfo _arg04;
            CorrectionInfo _arg05;
            KeyEvent _arg06;
            Bundle _arg1;
            InputContentInfo _arg07;
            Bundle _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg12 = data.readInt();
                            ICharSequenceResultCallback _arg22 = ICharSequenceResultCallback.Stub.asInterface(data.readStrongBinder());
                            getTextBeforeCursor(_arg08, _arg12, _arg22);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            int _arg13 = data.readInt();
                            ICharSequenceResultCallback _arg23 = ICharSequenceResultCallback.Stub.asInterface(data.readStrongBinder());
                            getTextAfterCursor(_arg09, _arg13, _arg23);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            IIntResultCallback _arg14 = IIntResultCallback.Stub.asInterface(data.readStrongBinder());
                            getCursorCapsMode(_arg010, _arg14);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ExtractedTextRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg15 = data.readInt();
                            IExtractedTextResultCallback _arg24 = IExtractedTextResultCallback.Stub.asInterface(data.readStrongBinder());
                            getExtractedText(_arg0, _arg15, _arg24);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int _arg16 = data.readInt();
                            deleteSurroundingText(_arg011, _arg16);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg17 = data.readInt();
                            deleteSurroundingTextInCodePoints(_arg012, _arg17);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg18 = data.readInt();
                            setComposingText(_arg02, _arg18);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            finishComposingText();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            int _arg19 = data.readInt();
                            commitText(_arg03, _arg19);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = CompletionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            commitCompletion(_arg04);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = CorrectionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            commitCorrection(_arg05);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int _arg110 = data.readInt();
                            setSelection(_arg013, _arg110);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            performEditorAction(_arg014);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            performContextMenuAction(_arg015);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            beginBatchEdit();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            endBatchEdit();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = KeyEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            sendKeyEvent(_arg06);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            clearMetaKeyStates(_arg016);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            performSpellCheck();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            performPrivateCommand(_arg017, _arg1);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg018 = data.readInt();
                            int _arg111 = data.readInt();
                            setComposingRegion(_arg018, _arg111);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            ICharSequenceResultCallback _arg112 = ICharSequenceResultCallback.Stub.asInterface(data.readStrongBinder());
                            getSelectedText(_arg019, _arg112);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            IIntResultCallback _arg113 = IIntResultCallback.Stub.asInterface(data.readStrongBinder());
                            requestUpdateCursorAnchorInfo(_arg020, _arg113);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = InputContentInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            int _arg114 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            IIntResultCallback _arg3 = IIntResultCallback.Stub.asInterface(data.readStrongBinder());
                            commitContent(_arg07, _arg114, _arg2, _arg3);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            int _arg115 = data.readInt();
                            int _arg25 = data.readInt();
                            ISurroundingTextResultCallback _arg32 = ISurroundingTextResultCallback.Stub.asInterface(data.readStrongBinder());
                            getSurroundingText(_arg021, _arg115, _arg25, _arg32);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            boolean _arg022 = data.readInt() != 0;
                            setImeConsumesInput(_arg022);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IInputContext {
            public static IInputContext sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.android.internal.view.IInputContext
            public void getTextBeforeCursor(int length, int flags, ICharSequenceResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(length);
                    _data.writeInt(flags);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getTextBeforeCursor(length, flags, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void getTextAfterCursor(int length, int flags, ICharSequenceResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(length);
                    _data.writeInt(flags);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getTextAfterCursor(length, flags, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void getCursorCapsMode(int reqModes, IIntResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(reqModes);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getCursorCapsMode(reqModes, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void getExtractedText(ExtractedTextRequest request, int flags, IExtractedTextResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(flags);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getExtractedText(request, flags, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void deleteSurroundingText(int beforeLength, int afterLength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(beforeLength);
                    _data.writeInt(afterLength);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteSurroundingText(beforeLength, afterLength);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(beforeLength);
                    _data.writeInt(afterLength);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteSurroundingTextInCodePoints(beforeLength, afterLength);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void setComposingText(CharSequence text, int newCursorPosition) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (text != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(text, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(newCursorPosition);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setComposingText(text, newCursorPosition);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void finishComposingText() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishComposingText();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void commitText(CharSequence text, int newCursorPosition) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (text != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(text, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(newCursorPosition);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().commitText(text, newCursorPosition);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void commitCompletion(CompletionInfo completion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (completion != null) {
                        _data.writeInt(1);
                        completion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().commitCompletion(completion);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void commitCorrection(CorrectionInfo correction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (correction != null) {
                        _data.writeInt(1);
                        correction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().commitCorrection(correction);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void setSelection(int start, int end) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(start);
                    _data.writeInt(end);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSelection(start, end);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void performEditorAction(int actionCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(actionCode);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().performEditorAction(actionCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void performContextMenuAction(int id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(id);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().performContextMenuAction(id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void beginBatchEdit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().beginBatchEdit();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void endBatchEdit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().endBatchEdit();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void sendKeyEvent(KeyEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendKeyEvent(event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void clearMetaKeyStates(int states) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(states);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearMetaKeyStates(states);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void performSpellCheck() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().performSpellCheck();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void performPrivateCommand(String action, Bundle data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(action);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().performPrivateCommand(action, data);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void setComposingRegion(int start, int end) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(start);
                    _data.writeInt(end);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setComposingRegion(start, end);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void getSelectedText(int flags, ICharSequenceResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getSelectedText(flags, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void requestUpdateCursorAnchorInfo(int cursorUpdateMode, IIntResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cursorUpdateMode);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUpdateCursorAnchorInfo(cursorUpdateMode, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts, IIntResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputContentInfo != null) {
                        _data.writeInt(1);
                        inputContentInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(flags);
                    if (opts != null) {
                        _data.writeInt(1);
                        opts.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().commitContent(inputContentInfo, flags, opts, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void getSurroundingText(int beforeLength, int afterLength, int flags, ISurroundingTextResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(beforeLength);
                    _data.writeInt(afterLength);
                    _data.writeInt(flags);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getSurroundingText(beforeLength, afterLength, flags, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInputContext
            public void setImeConsumesInput(boolean imeConsumesInput) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(imeConsumesInput ? 1 : 0);
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setImeConsumesInput(imeConsumesInput);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputContext impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInputContext getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
