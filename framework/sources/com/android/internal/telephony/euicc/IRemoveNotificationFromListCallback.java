package com.android.internal.telephony.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IRemoveNotificationFromListCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.euicc.IRemoveNotificationFromListCallback";

    void onComplete(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IRemoveNotificationFromListCallback {
        @Override // com.android.internal.telephony.euicc.IRemoveNotificationFromListCallback
        public void onComplete(int resultCode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IRemoveNotificationFromListCallback {
        static final int TRANSACTION_onComplete = 1;

        public Stub() {
            attachInterface(this, IRemoveNotificationFromListCallback.DESCRIPTOR);
        }

        public static IRemoveNotificationFromListCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRemoveNotificationFromListCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoveNotificationFromListCallback)) {
                return new Proxy(obj);
            }
            return (IRemoveNotificationFromListCallback) iin;
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
                    reply.writeString(IRemoveNotificationFromListCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRemoveNotificationFromListCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onComplete(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IRemoveNotificationFromListCallback {
            public static IRemoveNotificationFromListCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRemoveNotificationFromListCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.euicc.IRemoveNotificationFromListCallback
            public void onComplete(int resultCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRemoveNotificationFromListCallback.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(resultCode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoveNotificationFromListCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRemoveNotificationFromListCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
