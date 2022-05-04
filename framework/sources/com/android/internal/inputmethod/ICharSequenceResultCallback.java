package com.android.internal.inputmethod;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
/* loaded from: classes4.dex */
public interface ICharSequenceResultCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.inputmethod.ICharSequenceResultCallback";

    void onResult(CharSequence charSequence) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ICharSequenceResultCallback {
        @Override // com.android.internal.inputmethod.ICharSequenceResultCallback
        public void onResult(CharSequence result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ICharSequenceResultCallback {
        static final int TRANSACTION_onResult = 1;

        public Stub() {
            attachInterface(this, ICharSequenceResultCallback.DESCRIPTOR);
        }

        public static ICharSequenceResultCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICharSequenceResultCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICharSequenceResultCallback)) {
                return new Proxy(obj);
            }
            return (ICharSequenceResultCallback) iin;
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
            CharSequence _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ICharSequenceResultCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICharSequenceResultCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
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
        public static class Proxy implements ICharSequenceResultCallback {
            public static ICharSequenceResultCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICharSequenceResultCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.inputmethod.ICharSequenceResultCallback
            public void onResult(CharSequence result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICharSequenceResultCallback.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(result, _data, 0);
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

        public static boolean setDefaultImpl(ICharSequenceResultCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICharSequenceResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
