package android.service.translation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.translation.TranslationResponse;
/* loaded from: classes3.dex */
public interface ITranslationCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.translation.ITranslationCallback";

    void onTranslationResponse(TranslationResponse translationResponse) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITranslationCallback {
        @Override // android.service.translation.ITranslationCallback
        public void onTranslationResponse(TranslationResponse translationResponse) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITranslationCallback {
        static final int TRANSACTION_onTranslationResponse = 1;

        public Stub() {
            attachInterface(this, ITranslationCallback.DESCRIPTOR);
        }

        public static ITranslationCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITranslationCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITranslationCallback)) {
                return new Proxy(obj);
            }
            return (ITranslationCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onTranslationResponse";
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
            TranslationResponse _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITranslationCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITranslationCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TranslationResponse.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onTranslationResponse(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITranslationCallback {
            public static ITranslationCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITranslationCallback.DESCRIPTOR;
            }

            @Override // android.service.translation.ITranslationCallback
            public void onTranslationResponse(TranslationResponse translationResponse) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationCallback.DESCRIPTOR);
                    if (translationResponse != null) {
                        _data.writeInt(1);
                        translationResponse.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTranslationResponse(translationResponse);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITranslationCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITranslationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
