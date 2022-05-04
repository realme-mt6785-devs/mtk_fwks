package android.view.translation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ITranslationServiceCallback extends IInterface {
    public static final String DESCRIPTOR = "android.view.translation.ITranslationServiceCallback";

    void updateTranslationCapability(TranslationCapability translationCapability) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITranslationServiceCallback {
        @Override // android.view.translation.ITranslationServiceCallback
        public void updateTranslationCapability(TranslationCapability capability) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITranslationServiceCallback {
        static final int TRANSACTION_updateTranslationCapability = 1;

        public Stub() {
            attachInterface(this, ITranslationServiceCallback.DESCRIPTOR);
        }

        public static ITranslationServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITranslationServiceCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITranslationServiceCallback)) {
                return new Proxy(obj);
            }
            return (ITranslationServiceCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "updateTranslationCapability";
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
            TranslationCapability _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITranslationServiceCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITranslationServiceCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TranslationCapability.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            updateTranslationCapability(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITranslationServiceCallback {
            public static ITranslationServiceCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITranslationServiceCallback.DESCRIPTOR;
            }

            @Override // android.view.translation.ITranslationServiceCallback
            public void updateTranslationCapability(TranslationCapability capability) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationServiceCallback.DESCRIPTOR);
                    if (capability != null) {
                        _data.writeInt(1);
                        capability.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateTranslationCapability(capability);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITranslationServiceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITranslationServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
