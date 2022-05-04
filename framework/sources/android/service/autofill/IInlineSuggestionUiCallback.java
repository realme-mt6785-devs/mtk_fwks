package android.service.autofill;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.IInlineSuggestionUi;
import android.view.SurfaceControlViewHost;
/* loaded from: classes3.dex */
public interface IInlineSuggestionUiCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.autofill.IInlineSuggestionUiCallback";

    void onClick() throws RemoteException;

    void onContent(IInlineSuggestionUi iInlineSuggestionUi, SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) throws RemoteException;

    void onError() throws RemoteException;

    void onLongClick() throws RemoteException;

    void onStartIntentSender(IntentSender intentSender) throws RemoteException;

    void onTransferTouchFocusToImeWindow(IBinder iBinder, int i) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IInlineSuggestionUiCallback {
        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onClick() throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onLongClick() throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onContent(IInlineSuggestionUi content, SurfaceControlViewHost.SurfacePackage surface, int width, int height) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onError() throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onTransferTouchFocusToImeWindow(IBinder sourceInputToken, int displayId) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onStartIntentSender(IntentSender intentSender) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IInlineSuggestionUiCallback {
        static final int TRANSACTION_onClick = 1;
        static final int TRANSACTION_onContent = 3;
        static final int TRANSACTION_onError = 4;
        static final int TRANSACTION_onLongClick = 2;
        static final int TRANSACTION_onStartIntentSender = 6;
        static final int TRANSACTION_onTransferTouchFocusToImeWindow = 5;

        public Stub() {
            attachInterface(this, IInlineSuggestionUiCallback.DESCRIPTOR);
        }

        public static IInlineSuggestionUiCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInlineSuggestionUiCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInlineSuggestionUiCallback)) {
                return new Proxy(obj);
            }
            return (IInlineSuggestionUiCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onClick";
                case 2:
                    return "onLongClick";
                case 3:
                    return "onContent";
                case 4:
                    return "onError";
                case 5:
                    return "onTransferTouchFocusToImeWindow";
                case 6:
                    return "onStartIntentSender";
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
            SurfaceControlViewHost.SurfacePackage _arg1;
            IntentSender _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IInlineSuggestionUiCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInlineSuggestionUiCallback.DESCRIPTOR);
                            onClick();
                            return true;
                        case 2:
                            data.enforceInterface(IInlineSuggestionUiCallback.DESCRIPTOR);
                            onLongClick();
                            return true;
                        case 3:
                            data.enforceInterface(IInlineSuggestionUiCallback.DESCRIPTOR);
                            IInlineSuggestionUi _arg02 = IInlineSuggestionUi.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg2 = data.readInt();
                            int _arg3 = data.readInt();
                            onContent(_arg02, _arg1, _arg2, _arg3);
                            return true;
                        case 4:
                            data.enforceInterface(IInlineSuggestionUiCallback.DESCRIPTOR);
                            onError();
                            return true;
                        case 5:
                            data.enforceInterface(IInlineSuggestionUiCallback.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            int _arg12 = data.readInt();
                            onTransferTouchFocusToImeWindow(_arg03, _arg12);
                            return true;
                        case 6:
                            data.enforceInterface(IInlineSuggestionUiCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = IntentSender.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onStartIntentSender(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IInlineSuggestionUiCallback {
            public static IInlineSuggestionUiCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInlineSuggestionUiCallback.DESCRIPTOR;
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onClick() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionUiCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onClick();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onLongClick() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionUiCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLongClick();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onContent(IInlineSuggestionUi content, SurfaceControlViewHost.SurfacePackage surface, int width, int height) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionUiCallback.DESCRIPTOR);
                    _data.writeStrongBinder(content != null ? content.asBinder() : null);
                    if (surface != null) {
                        _data.writeInt(1);
                        surface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(width);
                    _data.writeInt(height);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onContent(content, surface, width, height);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onError() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionUiCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onTransferTouchFocusToImeWindow(IBinder sourceInputToken, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionUiCallback.DESCRIPTOR);
                    _data.writeStrongBinder(sourceInputToken);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTransferTouchFocusToImeWindow(sourceInputToken, displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onStartIntentSender(IntentSender intentSender) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionUiCallback.DESCRIPTOR);
                    if (intentSender != null) {
                        _data.writeInt(1);
                        intentSender.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStartIntentSender(intentSender);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionUiCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInlineSuggestionUiCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
