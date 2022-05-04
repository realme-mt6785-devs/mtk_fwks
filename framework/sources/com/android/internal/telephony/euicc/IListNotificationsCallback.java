package com.android.internal.telephony.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.euicc.EuiccNotification;
/* loaded from: classes4.dex */
public interface IListNotificationsCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.euicc.IListNotificationsCallback";

    void onComplete(int i, EuiccNotification[] euiccNotificationArr) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IListNotificationsCallback {
        @Override // com.android.internal.telephony.euicc.IListNotificationsCallback
        public void onComplete(int resultCode, EuiccNotification[] notifications) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IListNotificationsCallback {
        static final int TRANSACTION_onComplete = 1;

        public Stub() {
            attachInterface(this, IListNotificationsCallback.DESCRIPTOR);
        }

        public static IListNotificationsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IListNotificationsCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IListNotificationsCallback)) {
                return new Proxy(obj);
            }
            return (IListNotificationsCallback) iin;
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
                    reply.writeString(IListNotificationsCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IListNotificationsCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            EuiccNotification[] _arg1 = (EuiccNotification[]) data.createTypedArray(EuiccNotification.CREATOR);
                            onComplete(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IListNotificationsCallback {
            public static IListNotificationsCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IListNotificationsCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.euicc.IListNotificationsCallback
            public void onComplete(int resultCode, EuiccNotification[] notifications) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IListNotificationsCallback.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    _data.writeTypedArray(notifications, 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(resultCode, notifications);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IListNotificationsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IListNotificationsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
