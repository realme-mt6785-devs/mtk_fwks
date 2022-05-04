package android.service.textclassifier;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ITextClassifierCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.textclassifier.ITextClassifierCallback";

    void onFailure() throws RemoteException;

    void onSuccess(Bundle bundle) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITextClassifierCallback {
        @Override // android.service.textclassifier.ITextClassifierCallback
        public void onSuccess(Bundle result) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierCallback
        public void onFailure() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITextClassifierCallback {
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onSuccess = 1;

        public Stub() {
            attachInterface(this, ITextClassifierCallback.DESCRIPTOR);
        }

        public static ITextClassifierCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITextClassifierCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITextClassifierCallback)) {
                return new Proxy(obj);
            }
            return (ITextClassifierCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onSuccess";
                case 2:
                    return "onFailure";
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
            Bundle _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITextClassifierCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITextClassifierCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onSuccess(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(ITextClassifierCallback.DESCRIPTOR);
                            onFailure();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITextClassifierCallback {
            public static ITextClassifierCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITextClassifierCallback.DESCRIPTOR;
            }

            @Override // android.service.textclassifier.ITextClassifierCallback
            public void onSuccess(Bundle result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITextClassifierCallback.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuccess(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierCallback
            public void onFailure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITextClassifierCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFailure();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITextClassifierCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITextClassifierCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
