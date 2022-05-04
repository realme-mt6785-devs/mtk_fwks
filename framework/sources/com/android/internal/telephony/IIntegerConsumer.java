package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IIntegerConsumer extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.IIntegerConsumer";

    void accept(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IIntegerConsumer {
        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(int result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IIntegerConsumer {
        static final int TRANSACTION_accept = 1;

        public Stub() {
            attachInterface(this, IIntegerConsumer.DESCRIPTOR);
        }

        public static IIntegerConsumer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IIntegerConsumer.DESCRIPTOR);
            if (iin == null || !(iin instanceof IIntegerConsumer)) {
                return new Proxy(obj);
            }
            return (IIntegerConsumer) iin;
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
                    reply.writeString(IIntegerConsumer.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IIntegerConsumer.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            accept(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IIntegerConsumer {
            public static IIntegerConsumer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IIntegerConsumer.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.IIntegerConsumer
            public void accept(int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIntegerConsumer.DESCRIPTOR);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().accept(result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIntegerConsumer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IIntegerConsumer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
