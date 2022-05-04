package com.android.internal.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.autofill.AutofillId;
import android.view.inputmethod.InlineSuggestionsRequest;
import com.android.internal.view.IInlineSuggestionsResponseCallback;
/* loaded from: classes4.dex */
public interface IInlineSuggestionsRequestCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.view.IInlineSuggestionsRequestCallback";

    void onInlineSuggestionsRequest(InlineSuggestionsRequest inlineSuggestionsRequest, IInlineSuggestionsResponseCallback iInlineSuggestionsResponseCallback) throws RemoteException;

    void onInlineSuggestionsSessionInvalidated() throws RemoteException;

    void onInlineSuggestionsUnsupported() throws RemoteException;

    void onInputMethodFinishInput() throws RemoteException;

    void onInputMethodFinishInputView() throws RemoteException;

    void onInputMethodShowInputRequested(boolean z) throws RemoteException;

    void onInputMethodStartInput(AutofillId autofillId) throws RemoteException;

    void onInputMethodStartInputView() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IInlineSuggestionsRequestCallback {
        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInlineSuggestionsUnsupported() throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInlineSuggestionsRequest(InlineSuggestionsRequest request, IInlineSuggestionsResponseCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodStartInput(AutofillId imeFieldId) throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodShowInputRequested(boolean requestResult) throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodStartInputView() throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodFinishInputView() throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodFinishInput() throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInlineSuggestionsSessionInvalidated() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IInlineSuggestionsRequestCallback {
        static final int TRANSACTION_onInlineSuggestionsRequest = 2;
        static final int TRANSACTION_onInlineSuggestionsSessionInvalidated = 8;
        static final int TRANSACTION_onInlineSuggestionsUnsupported = 1;
        static final int TRANSACTION_onInputMethodFinishInput = 7;
        static final int TRANSACTION_onInputMethodFinishInputView = 6;
        static final int TRANSACTION_onInputMethodShowInputRequested = 4;
        static final int TRANSACTION_onInputMethodStartInput = 3;
        static final int TRANSACTION_onInputMethodStartInputView = 5;

        public Stub() {
            attachInterface(this, IInlineSuggestionsRequestCallback.DESCRIPTOR);
        }

        public static IInlineSuggestionsRequestCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInlineSuggestionsRequestCallback)) {
                return new Proxy(obj);
            }
            return (IInlineSuggestionsRequestCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onInlineSuggestionsUnsupported";
                case 2:
                    return "onInlineSuggestionsRequest";
                case 3:
                    return "onInputMethodStartInput";
                case 4:
                    return "onInputMethodShowInputRequested";
                case 5:
                    return "onInputMethodStartInputView";
                case 6:
                    return "onInputMethodFinishInputView";
                case 7:
                    return "onInputMethodFinishInput";
                case 8:
                    return "onInlineSuggestionsSessionInvalidated";
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
            InlineSuggestionsRequest _arg0;
            AutofillId _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                            onInlineSuggestionsUnsupported();
                            return true;
                        case 2:
                            data.enforceInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = InlineSuggestionsRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IInlineSuggestionsResponseCallback _arg1 = IInlineSuggestionsResponseCallback.Stub.asInterface(data.readStrongBinder());
                            onInlineSuggestionsRequest(_arg0, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onInputMethodStartInput(_arg02);
                            return true;
                        case 4:
                            data.enforceInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                            boolean _arg03 = data.readInt() != 0;
                            onInputMethodShowInputRequested(_arg03);
                            return true;
                        case 5:
                            data.enforceInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                            onInputMethodStartInputView();
                            return true;
                        case 6:
                            data.enforceInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                            onInputMethodFinishInputView();
                            return true;
                        case 7:
                            data.enforceInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                            onInputMethodFinishInput();
                            return true;
                        case 8:
                            data.enforceInterface(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                            onInlineSuggestionsSessionInvalidated();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IInlineSuggestionsRequestCallback {
            public static IInlineSuggestionsRequestCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInlineSuggestionsRequestCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInlineSuggestionsUnsupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInlineSuggestionsUnsupported();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInlineSuggestionsRequest(InlineSuggestionsRequest request, IInlineSuggestionsResponseCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInlineSuggestionsRequest(request, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodStartInput(AutofillId imeFieldId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    if (imeFieldId != null) {
                        _data.writeInt(1);
                        imeFieldId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInputMethodStartInput(imeFieldId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodShowInputRequested(boolean requestResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    _data.writeInt(requestResult ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInputMethodShowInputRequested(requestResult);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodStartInputView() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInputMethodStartInputView();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodFinishInputView() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInputMethodFinishInputView();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodFinishInput() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInputMethodFinishInput();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInlineSuggestionsSessionInvalidated() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionsRequestCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInlineSuggestionsSessionInvalidated();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionsRequestCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInlineSuggestionsRequestCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
