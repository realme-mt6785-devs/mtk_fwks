package com.android.internal.telephony.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface ILoadBoundProfilePackageCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.euicc.ILoadBoundProfilePackageCallback";

    void onComplete(int i, byte[] bArr) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ILoadBoundProfilePackageCallback {
        @Override // com.android.internal.telephony.euicc.ILoadBoundProfilePackageCallback
        public void onComplete(int resultCode, byte[] response) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ILoadBoundProfilePackageCallback {
        static final int TRANSACTION_onComplete = 1;

        public Stub() {
            attachInterface(this, ILoadBoundProfilePackageCallback.DESCRIPTOR);
        }

        public static ILoadBoundProfilePackageCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ILoadBoundProfilePackageCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ILoadBoundProfilePackageCallback)) {
                return new Proxy(obj);
            }
            return (ILoadBoundProfilePackageCallback) iin;
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
                    reply.writeString(ILoadBoundProfilePackageCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ILoadBoundProfilePackageCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            byte[] _arg1 = data.createByteArray();
                            onComplete(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ILoadBoundProfilePackageCallback {
            public static ILoadBoundProfilePackageCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ILoadBoundProfilePackageCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.euicc.ILoadBoundProfilePackageCallback
            public void onComplete(int resultCode, byte[] response) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILoadBoundProfilePackageCallback.DESCRIPTOR);
                    _data.writeInt(resultCode);
                    _data.writeByteArray(response);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(resultCode, response);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILoadBoundProfilePackageCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILoadBoundProfilePackageCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
