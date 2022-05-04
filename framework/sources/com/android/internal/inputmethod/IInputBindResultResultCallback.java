package com.android.internal.inputmethod;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.view.InputBindResult;
/* loaded from: classes4.dex */
public interface IInputBindResultResultCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.inputmethod.IInputBindResultResultCallback";

    void onError(ThrowableHolder throwableHolder) throws RemoteException;

    void onResult(InputBindResult inputBindResult) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IInputBindResultResultCallback {
        @Override // com.android.internal.inputmethod.IInputBindResultResultCallback
        public void onResult(InputBindResult result) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputBindResultResultCallback
        public void onError(ThrowableHolder exception) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IInputBindResultResultCallback {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResult = 1;

        public Stub() {
            attachInterface(this, IInputBindResultResultCallback.DESCRIPTOR);
        }

        public static IInputBindResultResultCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInputBindResultResultCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInputBindResultResultCallback)) {
                return new Proxy(obj);
            }
            return (IInputBindResultResultCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onResult";
                case 2:
                    return "onError";
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
            InputBindResult _arg0;
            ThrowableHolder _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IInputBindResultResultCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInputBindResultResultCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = InputBindResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onResult(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IInputBindResultResultCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ThrowableHolder.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onError(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IInputBindResultResultCallback {
            public static IInputBindResultResultCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInputBindResultResultCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.inputmethod.IInputBindResultResultCallback
            public void onResult(InputBindResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputBindResultResultCallback.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResult(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputBindResultResultCallback
            public void onError(ThrowableHolder exception) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputBindResultResultCallback.DESCRIPTOR);
                    if (exception != null) {
                        _data.writeInt(1);
                        exception.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(exception);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputBindResultResultCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInputBindResultResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
