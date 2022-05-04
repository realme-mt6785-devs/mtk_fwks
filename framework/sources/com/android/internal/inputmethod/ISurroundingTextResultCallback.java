package com.android.internal.inputmethod;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.inputmethod.SurroundingText;
/* loaded from: classes4.dex */
public interface ISurroundingTextResultCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.inputmethod.ISurroundingTextResultCallback";

    void onResult(SurroundingText surroundingText) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ISurroundingTextResultCallback {
        @Override // com.android.internal.inputmethod.ISurroundingTextResultCallback
        public void onResult(SurroundingText result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ISurroundingTextResultCallback {
        static final int TRANSACTION_onResult = 1;

        public Stub() {
            attachInterface(this, ISurroundingTextResultCallback.DESCRIPTOR);
        }

        public static ISurroundingTextResultCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISurroundingTextResultCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISurroundingTextResultCallback)) {
                return new Proxy(obj);
            }
            return (ISurroundingTextResultCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onResult";
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
            SurroundingText _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISurroundingTextResultCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISurroundingTextResultCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SurroundingText.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onResult(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ISurroundingTextResultCallback {
            public static ISurroundingTextResultCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISurroundingTextResultCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.inputmethod.ISurroundingTextResultCallback
            public void onResult(SurroundingText result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISurroundingTextResultCallback.DESCRIPTOR);
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
        }

        public static boolean setDefaultImpl(ISurroundingTextResultCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISurroundingTextResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
