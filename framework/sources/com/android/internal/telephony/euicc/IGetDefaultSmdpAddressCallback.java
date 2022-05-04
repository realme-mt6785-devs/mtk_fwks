package com.android.internal.telephony.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IGetDefaultSmdpAddressCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.euicc.IGetDefaultSmdpAddressCallback";

    void onComplete(int i, String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IGetDefaultSmdpAddressCallback {
        @Override // com.android.internal.telephony.euicc.IGetDefaultSmdpAddressCallback
        public void onComplete(int resultCode, String address) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IGetDefaultSmdpAddressCallback {
        static final int TRANSACTION_onComplete = 1;

        public Stub() {
            attachInterface(this, IGetDefaultSmdpAddressCallback.DESCRIPTOR);
        }

        public static IGetDefaultSmdpAddressCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IGetDefaultSmdpAddressCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IGetDefaultSmdpAddressCallback)) {
                return new Proxy(obj);
            }
            return (IGetDefaultSmdpAddressCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onComplete";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IGetDefaultSmdpAddressCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IGetDefaultSmdpAddressCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            String _arg1 = data.readString();
                            onComplete(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IGetDefaultSmdpAddressCallback {
            public static IGetDefaultSmdpAddressCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGetDefaultSmdpAddressCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.euicc.IGetDefaultSmdpAddressCallback
            public void onComplete(int resultCode, String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGetDefaultSmdpAddressCallback.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(resultCode, address);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGetDefaultSmdpAddressCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGetDefaultSmdpAddressCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
