package com.android.internal.inputmethod;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.inputmethod.InputMethodSubtype;
import com.android.internal.inputmethod.IBooleanResultCallback;
import com.android.internal.inputmethod.IIInputContentUriTokenResultCallback;
import com.android.internal.inputmethod.IVoidResultCallback;
/* loaded from: classes4.dex */
public interface IInputMethodPrivilegedOperations extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.inputmethod.IInputMethodPrivilegedOperations";

    void applyImeVisibilityAsync(IBinder iBinder, boolean z) throws RemoteException;

    void createInputContentUriToken(Uri uri, String str, IIInputContentUriTokenResultCallback iIInputContentUriTokenResultCallback) throws RemoteException;

    void hideMySoftInput(int i, IVoidResultCallback iVoidResultCallback) throws RemoteException;

    void notifyUserActionAsync() throws RemoteException;

    void reportFullscreenModeAsync(boolean z) throws RemoteException;

    void reportStartInputAsync(IBinder iBinder) throws RemoteException;

    void setImeWindowStatusAsync(int i, int i2) throws RemoteException;

    void setInputMethod(String str, IVoidResultCallback iVoidResultCallback) throws RemoteException;

    void setInputMethodAndSubtype(String str, InputMethodSubtype inputMethodSubtype, IVoidResultCallback iVoidResultCallback) throws RemoteException;

    void shouldOfferSwitchingToNextInputMethod(IBooleanResultCallback iBooleanResultCallback) throws RemoteException;

    void showMySoftInput(int i, IVoidResultCallback iVoidResultCallback) throws RemoteException;

    void switchToNextInputMethod(boolean z, IBooleanResultCallback iBooleanResultCallback) throws RemoteException;

    void switchToPreviousInputMethod(IBooleanResultCallback iBooleanResultCallback) throws RemoteException;

    void updateStatusIconAsync(String str, int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IInputMethodPrivilegedOperations {
        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void setImeWindowStatusAsync(int vis, int backDisposition) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void reportStartInputAsync(IBinder startInputToken) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void createInputContentUriToken(Uri contentUri, String packageName, IIInputContentUriTokenResultCallback resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void reportFullscreenModeAsync(boolean fullscreen) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void setInputMethod(String id, IVoidResultCallback resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void setInputMethodAndSubtype(String id, InputMethodSubtype subtype, IVoidResultCallback resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void hideMySoftInput(int flags, IVoidResultCallback resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void showMySoftInput(int flags, IVoidResultCallback resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void updateStatusIconAsync(String packageName, int iconId) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void switchToPreviousInputMethod(IBooleanResultCallback resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void switchToNextInputMethod(boolean onlyCurrentIme, IBooleanResultCallback resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void shouldOfferSwitchingToNextInputMethod(IBooleanResultCallback resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void notifyUserActionAsync() throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void applyImeVisibilityAsync(IBinder showOrHideInputToken, boolean setVisible) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IInputMethodPrivilegedOperations {
        static final int TRANSACTION_applyImeVisibilityAsync = 14;
        static final int TRANSACTION_createInputContentUriToken = 3;
        static final int TRANSACTION_hideMySoftInput = 7;
        static final int TRANSACTION_notifyUserActionAsync = 13;
        static final int TRANSACTION_reportFullscreenModeAsync = 4;
        static final int TRANSACTION_reportStartInputAsync = 2;
        static final int TRANSACTION_setImeWindowStatusAsync = 1;
        static final int TRANSACTION_setInputMethod = 5;
        static final int TRANSACTION_setInputMethodAndSubtype = 6;
        static final int TRANSACTION_shouldOfferSwitchingToNextInputMethod = 12;
        static final int TRANSACTION_showMySoftInput = 8;
        static final int TRANSACTION_switchToNextInputMethod = 11;
        static final int TRANSACTION_switchToPreviousInputMethod = 10;
        static final int TRANSACTION_updateStatusIconAsync = 9;

        public Stub() {
            attachInterface(this, IInputMethodPrivilegedOperations.DESCRIPTOR);
        }

        public static IInputMethodPrivilegedOperations asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInputMethodPrivilegedOperations)) {
                return new Proxy(obj);
            }
            return (IInputMethodPrivilegedOperations) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setImeWindowStatusAsync";
                case 2:
                    return "reportStartInputAsync";
                case 3:
                    return "createInputContentUriToken";
                case 4:
                    return "reportFullscreenModeAsync";
                case 5:
                    return "setInputMethod";
                case 6:
                    return "setInputMethodAndSubtype";
                case 7:
                    return "hideMySoftInput";
                case 8:
                    return "showMySoftInput";
                case 9:
                    return "updateStatusIconAsync";
                case 10:
                    return "switchToPreviousInputMethod";
                case 11:
                    return "switchToNextInputMethod";
                case 12:
                    return "shouldOfferSwitchingToNextInputMethod";
                case 13:
                    return "notifyUserActionAsync";
                case 14:
                    return "applyImeVisibilityAsync";
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
            Uri _arg0;
            InputMethodSubtype _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg12 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            setImeWindowStatusAsync(_arg02, data.readInt());
                            return true;
                        case 2:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            reportStartInputAsync(_arg03);
                            return true;
                        case 3:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            String _arg13 = data.readString();
                            IIInputContentUriTokenResultCallback _arg2 = IIInputContentUriTokenResultCallback.Stub.asInterface(data.readStrongBinder());
                            createInputContentUriToken(_arg0, _arg13, _arg2);
                            return true;
                        case 4:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg12 = true;
                            }
                            reportFullscreenModeAsync(_arg12);
                            return true;
                        case 5:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            String _arg04 = data.readString();
                            setInputMethod(_arg04, IVoidResultCallback.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 6:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = InputMethodSubtype.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IVoidResultCallback _arg22 = IVoidResultCallback.Stub.asInterface(data.readStrongBinder());
                            setInputMethodAndSubtype(_arg05, _arg1, _arg22);
                            return true;
                        case 7:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            hideMySoftInput(_arg06, IVoidResultCallback.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 8:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            showMySoftInput(_arg07, IVoidResultCallback.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 9:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            String _arg08 = data.readString();
                            updateStatusIconAsync(_arg08, data.readInt());
                            return true;
                        case 10:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            IBooleanResultCallback _arg09 = IBooleanResultCallback.Stub.asInterface(data.readStrongBinder());
                            switchToPreviousInputMethod(_arg09);
                            return true;
                        case 11:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg12 = true;
                            }
                            switchToNextInputMethod(_arg12, IBooleanResultCallback.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 12:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            IBooleanResultCallback _arg010 = IBooleanResultCallback.Stub.asInterface(data.readStrongBinder());
                            shouldOfferSwitchingToNextInputMethod(_arg010);
                            return true;
                        case 13:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            notifyUserActionAsync();
                            return true;
                        case 14:
                            data.enforceInterface(IInputMethodPrivilegedOperations.DESCRIPTOR);
                            IBinder _arg011 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg12 = true;
                            }
                            applyImeVisibilityAsync(_arg011, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IInputMethodPrivilegedOperations {
            public static IInputMethodPrivilegedOperations sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInputMethodPrivilegedOperations.DESCRIPTOR;
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void setImeWindowStatusAsync(int vis, int backDisposition) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeInt(vis);
                    _data.writeInt(backDisposition);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setImeWindowStatusAsync(vis, backDisposition);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void reportStartInputAsync(IBinder startInputToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeStrongBinder(startInputToken);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportStartInputAsync(startInputToken);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void createInputContentUriToken(Uri contentUri, String packageName, IIInputContentUriTokenResultCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    if (contentUri != null) {
                        _data.writeInt(1);
                        contentUri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createInputContentUriToken(contentUri, packageName, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void reportFullscreenModeAsync(boolean fullscreen) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeInt(fullscreen ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportFullscreenModeAsync(fullscreen);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void setInputMethod(String id, IVoidResultCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeString(id);
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInputMethod(id, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void setInputMethodAndSubtype(String id, InputMethodSubtype subtype, IVoidResultCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeString(id);
                    if (subtype != null) {
                        _data.writeInt(1);
                        subtype.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInputMethodAndSubtype(id, subtype, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void hideMySoftInput(int flags, IVoidResultCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeInt(flags);
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideMySoftInput(flags, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void showMySoftInput(int flags, IVoidResultCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeInt(flags);
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showMySoftInput(flags, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void updateStatusIconAsync(String packageName, int iconId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(iconId);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateStatusIconAsync(packageName, iconId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void switchToPreviousInputMethod(IBooleanResultCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchToPreviousInputMethod(resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void switchToNextInputMethod(boolean onlyCurrentIme, IBooleanResultCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeInt(onlyCurrentIme ? 1 : 0);
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchToNextInputMethod(onlyCurrentIme, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void shouldOfferSwitchingToNextInputMethod(IBooleanResultCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().shouldOfferSwitchingToNextInputMethod(resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void notifyUserActionAsync() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyUserActionAsync();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void applyImeVisibilityAsync(IBinder showOrHideInputToken, boolean setVisible) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputMethodPrivilegedOperations.DESCRIPTOR);
                    _data.writeStrongBinder(showOrHideInputToken);
                    _data.writeInt(setVisible ? 1 : 0);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyImeVisibilityAsync(showOrHideInputToken, setVisible);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputMethodPrivilegedOperations impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInputMethodPrivilegedOperations getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
