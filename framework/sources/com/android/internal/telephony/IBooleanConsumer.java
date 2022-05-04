package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IBooleanConsumer extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.IBooleanConsumer";

    void accept(boolean z) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IBooleanConsumer {
        @Override // com.android.internal.telephony.IBooleanConsumer
        public void accept(boolean result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IBooleanConsumer {
        static final int TRANSACTION_accept = 1;

        public Stub() {
            attachInterface(this, IBooleanConsumer.DESCRIPTOR);
        }

        public static IBooleanConsumer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBooleanConsumer.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBooleanConsumer)) {
                return new Proxy(obj);
            }
            return (IBooleanConsumer) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "accept";
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
                    reply.writeString(IBooleanConsumer.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBooleanConsumer.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            accept(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IBooleanConsumer {
            public static IBooleanConsumer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBooleanConsumer.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.IBooleanConsumer
            public void accept(boolean result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBooleanConsumer.DESCRIPTOR);
                    _data.writeInt(result ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().accept(result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBooleanConsumer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBooleanConsumer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
