package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusProtectConnection extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusProtectConnection";

    void onError(int i) throws RemoteException;

    void onSuccess() throws RemoteException;

    void onTimeout() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusProtectConnection {
        @Override // com.oplus.app.IOplusProtectConnection
        public void onSuccess() throws RemoteException {
        }

        @Override // com.oplus.app.IOplusProtectConnection
        public void onError(int errorCode) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusProtectConnection
        public void onTimeout() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusProtectConnection {
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onSuccess = 1;
        static final int TRANSACTION_onTimeout = 3;

        public Stub() {
            attachInterface(this, IOplusProtectConnection.DESCRIPTOR);
        }

        public static IOplusProtectConnection asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusProtectConnection.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusProtectConnection)) {
                return new Proxy(obj);
            }
            return (IOplusProtectConnection) iin;
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
                    return "onError";
                case 3:
                    return "onTimeout";
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
                    reply.writeString(IOplusProtectConnection.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusProtectConnection.DESCRIPTOR);
                            onSuccess();
                            return true;
                        case 2:
                            data.enforceInterface(IOplusProtectConnection.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onError(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusProtectConnection.DESCRIPTOR);
                            onTimeout();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusProtectConnection {
            public static IOplusProtectConnection sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusProtectConnection.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusProtectConnection
            public void onSuccess() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusProtectConnection.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuccess();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusProtectConnection
            public void onError(int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusProtectConnection.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(errorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusProtectConnection
            public void onTimeout() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusProtectConnection.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTimeout();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusProtectConnection impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusProtectConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
