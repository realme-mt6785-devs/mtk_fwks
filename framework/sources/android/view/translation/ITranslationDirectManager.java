package android.view.translation;

import android.os.Binder;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.translation.ITranslationCallback;
/* loaded from: classes3.dex */
public interface ITranslationDirectManager extends IInterface {
    public static final String DESCRIPTOR = "android.view.translation.ITranslationDirectManager";

    void onFinishTranslationSession(int i) throws RemoteException;

    void onTranslationRequest(TranslationRequest translationRequest, int i, ICancellationSignal iCancellationSignal, ITranslationCallback iTranslationCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITranslationDirectManager {
        @Override // android.view.translation.ITranslationDirectManager
        public void onTranslationRequest(TranslationRequest request, int sessionId, ICancellationSignal transport, ITranslationCallback callback) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationDirectManager
        public void onFinishTranslationSession(int sessionId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITranslationDirectManager {
        static final int TRANSACTION_onFinishTranslationSession = 2;
        static final int TRANSACTION_onTranslationRequest = 1;

        public Stub() {
            attachInterface(this, ITranslationDirectManager.DESCRIPTOR);
        }

        public static ITranslationDirectManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITranslationDirectManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITranslationDirectManager)) {
                return new Proxy(obj);
            }
            return (ITranslationDirectManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onTranslationRequest";
                case 2:
                    return "onFinishTranslationSession";
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
            TranslationRequest _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITranslationDirectManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITranslationDirectManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TranslationRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg1 = data.readInt();
                            ICancellationSignal _arg2 = ICancellationSignal.Stub.asInterface(data.readStrongBinder());
                            ITranslationCallback _arg3 = ITranslationCallback.Stub.asInterface(data.readStrongBinder());
                            onTranslationRequest(_arg0, _arg1, _arg2, _arg3);
                            return true;
                        case 2:
                            data.enforceInterface(ITranslationDirectManager.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onFinishTranslationSession(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITranslationDirectManager {
            public static ITranslationDirectManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITranslationDirectManager.DESCRIPTOR;
            }

            @Override // android.view.translation.ITranslationDirectManager
            public void onTranslationRequest(TranslationRequest request, int sessionId, ICancellationSignal transport, ITranslationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationDirectManager.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(sessionId);
                    _data.writeStrongBinder(transport != null ? transport.asBinder() : null);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTranslationRequest(request, sessionId, transport, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.translation.ITranslationDirectManager
            public void onFinishTranslationSession(int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationDirectManager.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFinishTranslationSession(sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITranslationDirectManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITranslationDirectManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
